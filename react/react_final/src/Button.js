import './Button.css';

function Button1(props){
	return (
		<button 
			onClick={props.click}
			className={props.classNames}>
			{props.text}
		</button>
	)
}

const Button2 = ({text, className, click})=>{
	return(
		<button class={className} onClick={click}>{text}</button>
	)
}

export {Button1, Button2};