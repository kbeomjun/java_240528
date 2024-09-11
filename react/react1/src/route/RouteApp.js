import { useState } from 'react';
import { BrowserRouter, Route } from 'react-router-dom';
import { Link } from 'react-router-dom';
import { Routes } from 'react-router-dom';
import Home from './Home';
import PostList from './PostList';
import PostInsert from './PostInsert';
import PostDetail from './PostDetail';

function RouteApp(){
	let links = ['/', '/post/insert', '/post/list'];
	let titles = ['홈', '게시글 등록', '게시글 목록'];
	let [list, setList] = useState([
		{
			title : '새 공지',
			content : '새 공지사항',
			writer : 'admin',
			view : 0
		},
		{
			title : '공지',
			content : '공지사항',
			writer : 'admin',
			view : 0
		}
	]);

	function addPost(post){
		setList([post, ...list]);
	}
	function delPost(index){
		setList(list.filter((item, i)=>index != i));
	}

	return(
		<BrowserRouter>
			{/* 메뉴 */}
			<Nav links={links} titles={titles}/>
			{/* 메뉴에 맞는 컴포넌트를 연결 */}
			<Body list={list} addPost={addPost} delPost={delPost}/>
		</BrowserRouter>
	)
}

function Body({list, addPost, delPost}){
	return(
		<Routes>
			<Route path={"/"} exact element={<Home/>}/>
			<Route path={"/post/list"} element={<PostList list={list} addPost={addPost} delPost={delPost}/>}/>
			<Route path={"/post/insert"} element={<PostInsert/>}/>
			<Route path={"/post/detail/:id"} element={<PostDetail list={list}/>}/>
		</Routes>
	)
}
function Nav({links, titles}){
	return(
		<nav>
			<ul>
				{
					links.map((link, index)=>{
						return(
							<li key={"link" + index}>
								<Link to={link}>{titles[index]}</Link>
							</li>
						)
					})
				}
			</ul>
		</nav>
	)
}

export default RouteApp;