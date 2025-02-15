
import "./styles.css"


type Props = {
  nameButton: string;
};

export default function ButtonPrimary({nameButton}: Props) {
  return <div className="dsc-btn dsc-btn-blue">{nameButton}</div>;
}
