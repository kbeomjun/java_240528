import { useState } from "react";

function List1(){
	const list = ['축구', '야구', '농구', '배구', '음악감상'];
	let [hobby, setHobby] = useState([]);

	function click(e){
		if(e.target.checked && hobby.length <= 3){
			hobby.push(e.target.value);
		}
		else if(!e.target.checked){
			hobby = hobby.filter(item => item != e.target.value);
		}
		setHobby([...hobby]);
	}

	function isDisabled(value){
		return hobby.length == 3 && !hobby.includes(value);
	}

	return(
		<div>
			<h1>당신의 취미는?</h1>
			{
				list.map((value, index)=>{
					return (
						<label key={index}>
							<input type="checkbox" value={value} onClick={click} disabled={isDisabled(value)}/>{value}
						</label>
					)
				})
			}
			<ul>
				{
					hobby.map((value, index)=>{
						return (
							<li key={index}>
								{value}
							</li>
						)
					})
				}
			</ul>
		</div>
	);
}

export default List1;