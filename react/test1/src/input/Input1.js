import {useState} from 'react';

function Input1(){
	let [input, setInput] = useState("");
	let [flag, setFlag] = useState(true);
	
	function change(e){
		setInput(e.target.value);
	}
	function click(){
		setFlag(!flag);
	}

	return(
		<div>
			<input type="text" onChange={change} value={input} disabled={!flag}/>
			<button onClick={click}>버튼</button>
			<input type="text" onChange={change} value={input} disabled={flag}/>
		</div>
	);
}

export default Input1;