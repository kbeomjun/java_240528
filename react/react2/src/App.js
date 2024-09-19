import { BrowserRouter, Routes, Route } from "react-router-dom";
import PostList from './PostList';
import Main from './Main';
import Nav from "./Nav";

function App() {
  return (
      <BrowserRouter>
        <Nav />
      
        <Routes>
          <Route path={'/'} exact element={<Main/>}/>
          <Route path={'/post/list/:co_num'} element={<PostList/>}/>
        </Routes>
      </BrowserRouter>
  );
}

export default App;