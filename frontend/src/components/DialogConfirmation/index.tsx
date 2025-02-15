import ButtonInverse from "../ButtonInverse";
import ButtonPrimary from "../ButtonPrimary";

type Props = {
  id: number;
  message: string;
  onDialogAnswer: Function;
};
//        onClick={(event) => event.stopPropagation()} é para não fechar a janela ao clicar na area branca

export default function DialogConfirmation({
  id,
  message,
  onDialogAnswer,
}: Props) {
  return (
    <div
      className="dsc-dialog-background"
      onClick={() => onDialogAnswer(false, id)}
    >
      <div
        className="dsc-dialog-box"
        onClick={(event) => event.stopPropagation()}
      >
        <h2> {message} </h2>
        <div className="dsc-dialog-btn-container">
          <div onClick={() => onDialogAnswer(false, id)}>
            <ButtonInverse nameButton={"Não"} />
          </div>
          <div onClick={() => onDialogAnswer(true, id)}>
            <ButtonPrimary nameButton={"Sim"} />
          </div>
        </div>
      </div>
    </div>
  );
}
