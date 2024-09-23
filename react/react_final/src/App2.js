import { useState } from "react";

function App2(){
	let [list, setList] = useState([
		{
			title : '제목1',
			writer : '작성자1',
			content : '내용1'
		},
		{
			title : '제목2',
			writer : '작성자2',
			content : '내용2'
		}
	])
	let [title, setTitle] = useState('');
	let [writer, setWriter] = useState('');
	let [content, setContent] = useState('');
	const click = ()=>{
		let post ={
			title,
			writer,
			content
		};
		setList([post, ...list])
		setTitle('');
		setWriter('');
		setContent('');
	}
	
	return(
		<div>
			<div>
				<table border={1}>
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
						</tr>
					</thead>
					<tbody>
						{
							list.map((item, index)=>{
								return(
									<tr key={index}>
										<td>{index+1}</td>
										<td>{item.title}</td>
										<td>{item.writer}</td>
									</tr>
								)
							})
						}
					</tbody>
				</table>
			</div>
			<div>
				<Input text={"제목"} change={setTitle} value={title}/> 
				<br/>
				<Input text={"작성자"} change={setWriter} value={writer}/> 
				<br/>
				<textarea placeholder="내용" onChange={(e)=>setContent(e.target.value)} value={content}></textarea> 
				<br/>
				<button onClick={click}>등록</button>
			</div>
		</div>
	)
}

function Input({text, change, value}){
	return(
		<input type="text" placeholder={text} onChange={(e)=>change(e.target.value)} value={value}/>
	)
}

export default App2;