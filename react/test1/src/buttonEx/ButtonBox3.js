import './ButtonBox3.css';
import Button from "./Button";

function ButtonBox3(){
	let style = {
		color : "red",
		"font-size" : "24px"
	}
	
	return(
		<form>
			<div>
				<input type="text" name="test"/>
			</div>
			<Button text={"전송"} type={"submit"} classNames={"btn br-5"}/>
			<Button text={"버튼"} type={"button"} classNames={"btn"} style={style}/>
			<Button text={"리셋"} type={"reset"} classNames={"btn"}/>
		</form>
	);
}

export default ButtonBox3;