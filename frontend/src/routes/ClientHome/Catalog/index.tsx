import "./styles.css";
import SearchBar from "../../../components/SearchBar";

import ButtonNextPage from "../../../components/ButtonNextPage";

import CatalogCard from "../../../components/CatalogCard";
import * as productService from "../../../services/product-service";
import { useEffect, useState } from "react";
import { ProductDTO } from "../../../models/product";
import { isAuthenticated } from "../../../services/auth-service";

type QueryParams = {
  page: number;
  name: string;
};

export default function Catalog() {
  const [isLastPage, setIsLastPage] = useState(false);
  const [products, setProducts] = useState<ProductDTO[]>([]);

  const [queryParams, setQueryParams] = useState<QueryParams>({
    page: 0,
    name: "",
  });

  function handleNextPageClick() {
    setQueryParams({ ...queryParams, page: queryParams.page + 1 });
  }

  useEffect(() => {
    //localStorage.setItem("minhaCategoria", JSON.stringify(objTest))

    const obj = JSON.parse(localStorage.getItem("minhaCategoria") || "{}");

    productService
      .findPageRequest(queryParams.page, queryParams.name)
      .then((response) => {
        const nextPage = response.data.content;
        setProducts(products.concat(nextPage));
        setIsLastPage(response.data.last);
      });
  }, [queryParams]);

  function handleSearch(searchText: string) {
    setProducts([]); //esvaziar a lista ao fazer uma busca Nova
    setQueryParams({ ...queryParams, page: 0, name: searchText });
  } //destruturação ... -> o que já tinha só que o nome mudará

  return (
    <main>
      <section id="catalog-section" className="dsc-container">
        <SearchBar onSearch={handleSearch} />

        <div className="dsc-catalog-cards dsc-mb20 dsc-mt20">
          {products.map((product) => (
            <CatalogCard key={product.id} product={product} />
          ))}
        </div>

        {!isLastPage && (
          <ButtonNextPage
            nameButton="CARREGAR MAIS"
            onNextPage={handleNextPageClick}
          />
        )}
      </section>
    </main>
  );
}
