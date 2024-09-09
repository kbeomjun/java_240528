import {useState} from 'react';

function Todo(){
	let [todo, setTodo] = useState("");
	let [tmp, setTmp] = useState("");
	function inputChange(e){
		setTmp(e.target.value);
	}
	function btnClick(){
		setTodo(tmp);
	}
	
	return(
		<div>
			<input onChange={inputChange}/>
			<button onClick={btnClick}>버튼</button>
			<h1>{todo}</h1>
		</div>
	);
}

export default Todo;