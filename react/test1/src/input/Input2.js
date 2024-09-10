import {useState} from 'react';

function Input2(){
	let [time, setTime] = useState(0);
	let [flag, setFlag] = useState(true);

	return(
		<div>
			<input type="text" disabled={!flag} 
				value={!flag ? Math.floor(time / 60) : time} onChange={(e)=>setTime(e.target.value)}/>
			<label>분</label>
			<button onClick={()=>{setFlag(!flag); setTime(0)}}>전환</button>
			<br/>
			<input type="text" disabled={flag} 
				value={flag ? (time * 60) : time} onChange={(e)=>setTime(e.target.value)}/>
			<label>초</label>
		</div>
	);
}

export default Input2;