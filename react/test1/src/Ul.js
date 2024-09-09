import Li from "./Li";

function Ul(){
	var listTitle = ['등원', '수업', '점심', '수업', '하원'];
	function list(value){
		return <Li text={value} />
	}

	return(
		<ul>
			{
				// map은 a를 b로 변환해주는 함수
				listTitle.map((value, index, arr)=>{
					return <Li text={value} />
				})
			}
			{
				listTitle.map(list)
			}
		</ul>
	);
}

export default Ul;