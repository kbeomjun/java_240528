import {useState} from 'react';

function TodoList(){
	let [todo, setTodo] = useState("");
	let [todoList, setTodoList] = useState([]);
	function inputChange(e){
		setTodo(e.target.value);
	}
	function btnClick(){
		setTodoList([...todoList, todo]);
	}
	
	return(
		<div>
			<input onChange={inputChange}/>
			<button onClick={btnClick}>버튼</button>
			
			<h1>오늘의 할일</h1>
			<ul>
				{
					todoList.map((value, index, arr)=>{
						return <li>{value}</li>
					})
				}
			</ul>
		</div>
	);
}

export default TodoList;