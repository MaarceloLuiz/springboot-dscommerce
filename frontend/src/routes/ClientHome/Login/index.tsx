import "./styles.css";
import { useContext, useState } from "react";
import * as forms from "../../../utils/forms";
import * as authService from "../../../services/auth-service";
import { useNavigate } from "react-router-dom";
import { ContextToken } from "../../../services/context-token";
import FormInput from "../../../components/FormInput";

export default function Login() {
  const { setContextTokenPayload } = useContext(ContextToken);

  const navigate = useNavigate();

  const [submitResponseFail, setSubmitResponseFail] = useState(false);

  const [formData, setFormData] = useState<any>({
    username: {
      value: "",
      id: "username",
      name: "username",
      type: "text",
      placeholder: "Email",
      validation: function (value: string) {
        return /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(
          value.toLowerCase()
        );
      },
      message: "Favor informar um email válido",
    },
    password: {
      value: "",
      id: "password",
      name: "password",
      type: "password",
      placeholder: "Senha",
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

  function handleTurnDurty(name: string) {
    const newFormData = forms.dirtyAndValidate(formData, name);
    setFormData(newFormData);
  }

  function handleSubmit(event: any) {
    event.preventDefault();

    setSubmitResponseFail(false);

    const formDataValidated = forms.dirtyAndValidateAll(formData);
    if (forms.hasAnyInvalid(formDataValidated)) {
      setFormData(formDataValidated);
      return;
    }

    authService
      .loginRequest(forms.toValues(formData))
      .then((response) => {
        authService.saveAcessToken(response.data.access_token);
        setContextTokenPayload(authService.getAccessTokenPayload());
        navigate("/cart");
        // console.log(authService.getAccessTokenPayload()?.exp);
      })
      .catch(() => {
        setSubmitResponseFail(true);
        // console.log("Error no login", error);
      });
  }

  return (
    <main>
      <section id="login-section" className="dsc-container">
        <div className="dsc-login-form-container">
          <form className="dsc-card dsc-form" onSubmit={handleSubmit}>
            <h2>Login</h2>
            <div className="dsc-form-controls-container">
              <div>
                <FormInput
                  {...formData.username}
                  className="dsc-form-control"
                  onTurnDirty={handleTurnDurty}
                  onChange={handleInputChange}
                />
                <div className="dsc-form-error">
                  {formData.username.message}
                </div>
              </div>
              <div>
                <FormInput
                  {...formData.password}
                  className="dsc-form-control"
                  onTurnDirty={handleTurnDurty}
                  onChange={handleInputChange}
                />
              </div>
            </div>

            {submitResponseFail && (
              <div className="dsc-form-global-error">
                Usuario ou senha invalidos.
              </div>
            )}

            <div className="dsc-login-form-buttons dsc-mt20">
              <button type="submit" className="dsc-btn dsc-btn-blue">
                Entrar
              </button>
            </div>
          </form>
        </div>
      </section>
    </main>
  );
}
