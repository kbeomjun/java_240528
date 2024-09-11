import { useState } from 'react';
import './PostEx.css';
import Modal from './Modal';

function PostEx(){
	let [num, setNum] = useState(1);
	let [title, setTitle] = useState("");
	let [writer, setWriter] = useState("");
	let [content, setContent] = useState("");
	let [list, setList] = useState([]);
	let [modal, setModal] = useState(false);
	let [post, setPost] = useState({});

	function click(){
		let post = {
			num : num,
			title : title,
			writer : writer,
			content : content,
			view : 0
		}
		setNum(num + 1);
		setList([post, ...list]);
		setTitle("");
		setWriter("");
		setContent("");
	}

	return(
		<div>
			<div>
				<input type="text" id="title" name="title" placeholder='제목' 
					onChange={(e)=>setTitle(e.target.value)} value={title}/>
				<input type="text" id="writer" name="writer" placeholder='작성자' 
					onChange={(e)=>setWriter(e.target.value)} value={writer}/>
				<textarea id="content" name='content' placeholder='내용' 
					onChange={(e)=>setContent(e.target.value)} value={content}></textarea>
				<button className='btn' onClick={click}>게시글 등록</button>
			</div>

			<div id="table-box">
				<h1>게시글 목록</h1>
				<table border="1">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
						{
							list.map((value)=>{
								return (
									<tr>
										<td>{value.num}</td>
										<td onClick={()=>{setModal(!modal); setPost(value)}}>{value.title}</td>
										<td>{value.writer}</td>
										<td>{value.view}</td>
									</tr>
								)
							})
						}
					</tbody>
				</table>
			</div>
			{
				modal ? <Modal post={post} funcClose={()=>setModal(false)} /> : ''
			}
		</div>
	);
}

export default PostEx;