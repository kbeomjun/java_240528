import { Button, Form } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useState } from "react";

function Signup(){
	let [data, setData] = useState({
		me_id : '',
		me_pw : '',
		me_pw2 : '',
		me_email : '',
	});

	function submit(e){
		e.preventDefault();
		if(data.me_id === ''){
			alert('아이디는 필수항목입니다.');
			return;
		}
		if(data.me_pw === ''){
			alert('비번은 필수항목입니다.');
			return;
		}
		if(data.me_pw !== data.me_pw2){
			alert('비번과 일치하지 않습니다.');
			return;
		}
		if(data.me_email === ''){
			alert('이메일은 필수항목입니다.');
			return;
		}
		
		fetch("/spring/test/signup", {
			method : 'post',
			body : JSON.stringify(data),
			headers : {
				'Content-Type' : 'application/json'
			}
		})
			.then(res => res.json())
			.then((data) => {
        if(data){
          alert("회원가입에 성공했습니다.");
        }else{
          alert("회원가입에 실패했습니다.");
        }
      })
			.catch(e=>console.error(e));
	}

  function change(e){
		setData({
			...data,
			[e.target.name] : e.target.value
		})
	}

  return (
		<form onSubmit={submit}>
			<Form.Label htmlFor="me_id">아이디</Form.Label>
			<Form.Control type="text" id="me_id" name="me_id" aria-describedby="아이디" onChange={change}/>
			<Form.Label htmlFor="me_pw">비번</Form.Label>
			<Form.Control type="password" id="me_pw" name="me_pw" aria-describedby="비번" onChange={change}/>
			<Form.Label htmlFor="me_pw2">비번확인</Form.Label>
			<Form.Control type="password" id="me_pw2" name="me_pw2" aria-describedby="비번확인" onChange={change}/>
			<Form.Label htmlFor="me_email">이메일</Form.Label>
			<Form.Control type="email" id="me_email" name="me_email" aria-describedby="이메일" onChange={change}/>
			<Button type="submit" sm>회원가입</Button>
		</form>
  );
}

export default Signup;