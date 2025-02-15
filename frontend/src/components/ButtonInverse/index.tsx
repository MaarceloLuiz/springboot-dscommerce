import "./styles.css"


type Props = {
  nameButton: string;
};

export default function ButtonInverse({ nameButton }: Props) {
  return <div className="dsc-btn dsc-btn-white">{nameButton}</div>;
}
