import "./styles.css";

type Props = {
  nameButton: string;
  onNextPage: Function;
};

export default function ButtonNextPage({ nameButton, onNextPage }: Props) {
  return (
    <div onClick={() => onNextPage()} className="dsc-btn-next-page">
      {nameButton}
    </div>
  );
}
