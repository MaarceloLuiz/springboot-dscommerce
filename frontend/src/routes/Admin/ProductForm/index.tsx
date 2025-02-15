import { Link, useNavigate, useParams } from "react-router-dom";
import "./styles.css";
import { useEffect, useState } from "react";
import FormInput from "../../../components/FormInput";
import * as forms from "../../../utils/forms";
import * as productService from "../../../services/product-service";
import * as categoryService from "../../../services/category-service";
import FormTextArea from "../../../components/FormTextArea";
import { CategoryDTO } from "../../../models/category";
import FormSelect from "../../../components/FormSelect";
import { selectStyles } from "../../../utils/select";

export default function ProductForm() {
  const navigate = useNavigate();

  const params = useParams();

  const isEditing = params.productId !== "create";

  const [categories, setCategories] = useState<CategoryDTO[]>([]);

  const [formData, setFormData] = useState<any>({
    name: {
      value: "",
      id: "name",
      name: "name",
      type: "text",
      placeholder: "Nome",
      validation: function (value: string) {
        return /^.{3,80}$/.test(value);
      },
      message: "favor informar um nome entre 3 e 80 caracteres",
    },
    price: {
      value: "",
      id: "price",
      name: "price",
      type: "number",
      placeholder: "Preço",
      validation: function (value: any) {
        return Number(value) > 0;
      },
      message: "Apenas valor positivo",
    },
    imgUrl: {
      value: "",
      id: "imgUrl",
      name: "imgUrl",
      type: "text",
      placeholder: "Imagem",
    },
    description: {
      value: "",
      id: "description",
      name: "description",
      type: "text",
      placeholder: "Descrição",
      validation: function (value: string) {
        return /^.{10,}$/.test(value);
      },
      message: "Mínimo 10 caracteres",
    },
    categories: {
      value: [],
      id: "categories",
      name: "categories",
      placeholder: "Categorias",
      validation: function (value: CategoryDTO[]) {
        return value.length > 0;
      },
      message: "Escolha ao menos 1 categoria",
    },
  });

  function handleInputChange(event: any) {
    const result = forms.updateAndValidate(
      formData,
      event.target.name,
      event.target.value
    );
    setFormData(result);
  }

  function handleSubmit(event: any) {
    event.preventDefault();
    const formDataValidated = forms.dirtyAndValidateAll(formData);
    if (forms.hasAnyInvalid(formDataValidated)) {
      setFormData(formDataValidated);
      return;
    }
    const requestBody = forms.toValues(formData);
    if (isEditing) {
      requestBody.id = params.productId;
    }

    const request = isEditing
      ? productService.updateRequest(requestBody)
      : productService.insertRequest(requestBody);

    request
      .then(() => {
        navigate("/admin/products");
      })
      .catch((error) => {
        const newInputs = forms.setBackendErrors(
          formData,
          error.response.data.errors
        );
        setFormData(newInputs);
      });
  }

  function handleTurnDurty(name: string) {
    const newFormData = forms.dirtyAndValidate(formData, name);
    setFormData(newFormData);
  }

  useEffect(() => {
    categoryService.findAllRequest().then((response) => {
      setCategories(response.data);
    });
  }, []);

  useEffect(() => {
    if (isEditing) {
      productService.findById(Number(params.productId)).then((response) => {
        setFormData(forms.updateAll(formData, response.data));
        //USADO PARA ATUALIZAR O FORM DATA
      });
    }
  }, []);

  return (
    <main>
      <section id="product-form-section" className="dsc-container">
        <div className="dsc-product-form-container">
          <form className="dsc-card dsc-form" onSubmit={handleSubmit}>
            <h2>Dados do produto</h2>

            <div className="dsc-form-controls-container">
              <FormInput
                {...formData.name}
                className="dsc-form-control"
                onTurnDirty={handleTurnDurty}
                onChange={handleInputChange}
              />
              <div className="dsc-form-error">{formData.name.message}</div>
            </div>

            <div className="dsc-form-controls-container">
              <FormInput
                {...formData.price}
                className="dsc-form-control"
                onTurnDirty={handleTurnDurty}
                onChange={handleInputChange}
              />
              <div className="dsc-form-error">{formData.price.message}</div>
            </div>

            <div className="dsc-form-controls-container">
              <FormInput
                {...formData.imgUrl}
                className="dsc-form-control"
                onChange={handleInputChange}
                onTurnDirty={handleTurnDurty}
              />
            </div>

            <div className="dsc-form-controls-container ">
              <FormSelect
                {...formData.categories}
                className="dsc-form-control dsc-form-select-container"
                options={categories}
                onChange={(obj: any) => {
                  const newFormData = forms.updateAndValidate(
                    formData,
                    "categories",
                    obj
                  );
                  setFormData(newFormData);
                }}
                styles={selectStyles}
                onTurnDirty={handleTurnDurty}
                isMulti
                getOptionLabel={(obj) => obj.name}
                getOptionValue={(obj) => String(obj.id)}
              />
              <div className="dsc-form-error">
                {formData.categories.message}
              </div>
            </div>

            <div className="dsc-form-controls-container ">
              <FormTextArea
                {...formData.description}
                className="dsc-form-control dsc-textarea"
                onTurnDirty={handleTurnDurty}
                onChange={handleInputChange}
              />
              <div className="dsc-form-error ">
                {formData.description.message}
              </div>
            </div>

            <div className="dsc-product-form-buttons">
              <Link to={"/admin/products"}>
                <button type="reset" className="dsc-btn dsc-btn-white">
                  Cancelar
                </button>
              </Link>

              <button type="submit" className="dsc-btn dsc-btn-blue">
                Salvar
              </button>
            </div>
          </form>
        </div>
      </section>
    </main>
  );
}
