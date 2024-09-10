import {useState} from 'react';

function PaginationBox({maxPage}){
	let [page, setPage] = useState(1);

	function prev(){
		if(page == 1){
			setPage(maxPage);
		}else{
			setPage(page - 1);
		}
	}
	function next(){
		if(page == maxPage){
			setPage(1);
		}else{
			setPage(page + 1);
		}
	}
	
	return(
		<div>
			<button onClick={prev}>&lt;</button>
			<span>
				<span>{page}</span>
				<span>/</span>
				<span>{maxPage}</span>
			</span>
			<button onClick={next}>&gt;</button>
		</div>
	);
}

export default PaginationBox;