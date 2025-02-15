import "./styles.css";
import ButtonInverse from "../../../components/ButtonInverse";
import ButtonPrimary from "../../../components/ButtonPrimary";
import ProductDetailsCard from "../../../components/ProductDetailsCard";
import { useNavigate, useParams } from "react-router-dom";
import { Link } from "react-router-dom";
import { useContext, useEffect, useState } from "react";
import { ProductDTO } from "../../../models/product";
import * as productService from "../../../services/product-service";
import * as cartService from "../../../services/cart-service";
import { ContextCartCount } from "../../../services/context-cart";

export default function ProductDetails() {
  const params = useParams();

  const [product, setProduct] = useState<ProductDTO>();

  const { setContextCartCount } = useContext(ContextCartCount);

  const navigate = useNavigate();

  useEffect(() => {
    productService
      .findById(Number(params.productId))
      .then((response) => {
        setProduct(response.data);
      })
      .catch(() => {
        navigate("/");
      });
  }, []);

  function handleByClick() {
    if (product) {
      cartService.addProduct(product);
      setContextCartCount(cartService.getCart().items.length);
      navigate("/cart");
    }
  }

  return (
    <main>
      <section id="product-details-section" className="dsc-container">
        {product && <ProductDetailsCard product={product} />}

        <div className="dsc-btn-page-container">
          <div onClick={handleByClick}>
            <ButtonPrimary nameButton="COMPRAR" />
          </div>
          <Link to="/">
            <ButtonInverse nameButton="INICIO" />
          </Link>
        </div>
      </section>
    </main>
  );
}
