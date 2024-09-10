import { useState } from "react";

function Select(){
	let [sel, setSel] = useState("");

	return(
		<div>
			<select onChange={(e)=>setSel(e.target.value)}>
				<option value={""}>선택안함</option>
				<option>사과</option>
				<option>바나나</option>
				<option>오렌지</option>
			</select>

			<h1>{sel}</h1>
		</div>
	);
}

export default Select;