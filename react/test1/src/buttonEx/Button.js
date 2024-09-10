
function Button({text, type, classNames, style}){

	return(
		<button type={type} class={classNames} style={style}>{text}</button>
	);
}

export default Button;