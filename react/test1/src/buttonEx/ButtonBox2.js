import PaginationBox from './PaginationBox';

function ButtonBox2(){
	return(
		<div>
			<PaginationBox maxPage={4}/>
			<PaginationBox maxPage={13}/>
		</div>
	);
}

export default ButtonBox2;