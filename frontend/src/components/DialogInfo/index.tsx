import ButtonPrimary from "../ButtonPrimary";

type Props = {
  message: string;
  onDialogClose: Function;
};
//        onClick={(event) => event.stopPropagation()} é para não fechar a janela ao clicar na area branca

export default function DialogInfo({ message, onDialogClose }: Props) {
  return (
    <div className="dsc-dialog-background" onClick={() => onDialogClose()}>
      <div
        className="dsc-dialog-box"
        onClick={(event) => event.stopPropagation()}
      >
        <h2> {message} </h2>
        <div
          className="dsc-dialog-btn"
          onClick={() => onDialogClose()}
        >
          <ButtonPrimary nameButton={"OK"} />
        </div>
      </div>
    </div>
  );
}
