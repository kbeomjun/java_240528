import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Form, Container } from 'react-bootstrap';
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
		<Container>
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
        <div className="form-control" style={{ height: 'auto', overflow: 'auto', border: '1px solid #ced4da', borderRadius: '0.25rem', padding: '0.5rem' }}>
					<div dangerouslySetInnerHTML={{ __html: post.po_content }} />
				</div>
      </Form.Group>
		</Container>
	)
}

export default PostDetail;