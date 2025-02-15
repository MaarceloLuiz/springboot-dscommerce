import { createContext } from "react";


export type ContextCartCountType = {
    contextCartCount: number;
    setContextCartCount: (contextCartCount : number) => void; //especifica que é uma função que recebe um dado do tipo number e retorna em um void
}

export const ContextCartCount = createContext<ContextCartCountType>({
    contextCartCount: 0,
    setContextCartCount: () =>{}
})