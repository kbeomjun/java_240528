import './PostEx.css';

function Modal({post, funcClose}){
	return(
		<div id="modal">
			<div id="modal-content">
				<div>
					<div>번호</div>
					<div id="num">{post.num}</div>
				</div>
				<div>
					<div>제목</div>
					<div id="title">{post.title}</div>
				</div>
				<div>
					<div>작성자</div>
					<div id="writer">{post.writer}</div>
				</div>
				<div>
					<div>조회수</div>
					<div id="view">{post.view}</div>
				</div>
				<div>
					<div>내용</div>
					<div id="content">{post.content}</div>
				</div>
				<button className={'btn'} onClick={funcClose}>닫기</button>
			</div>
		</div>
	)
}

export default Modal;