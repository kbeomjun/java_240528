import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Form } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

function PostDetail(){
	let [post, setPost] = useState({});
	const {po_num} = useParams();

	useEffect(() => {
		fetch('/spring/react/post/detail/'+po_num)
			.then((res) => res.json())
			.then(res=>{
				res.po_date = (new Date(res.po_date)).toLocaleDateString();
				setPost(res);
			})
			.catch(e=>console.error(e));
	}, [po_num]);
	
	return(
		<div>
			<Form.Group className="mb-3">
        <Form.Label>제목</Form.Label>
        <Form.Control readOnly value={post.po_title}/>
      </Form.Group>
      <Form.Group className="mb-3">
        <Form.Label>작성자</Form.Label>
        <Form.Control readOnly value={post.po_me_id}/>
      </Form.Group>
			<Form.Group className="mb-3">
        <Form.Label>작성일</Form.Label>
        <Form.Control readOnly value={post.po_date}/>
      </Form.Group>
			<Form.Group className="mb-3">
        <Form.Label>조회수</Form.Label>
        <Form.Control readOnly value={post.po_view}/>
      </Form.Group>
			<Form.Group className="mb-3">
        <Form.Label>내용</Form.Label>
        <Form.Control as="textarea" rows={12} readOnly value={post.po_content}/>
      </Form.Group>
		</div>
	)
}

export default PostDetail;