# 분류별 등록된 제품수를 조회하는 쿼리
select ca_name '분류', count(pr_code) '제품수' from category left join product on pr_ca_num = ca_num group by ca_name;

# 옷으로 등록된 제품들을 조회하는 쿼리
select * from product join category on ca_num = pr_ca_num where ca_name = '옷';

# '시원한'이 들어간 제품을 검색 => 제목에 '시원한'이 포함된 제품을 조회하는 쿼리
select * from product where pr_name like concat('%', '시원한', '%');

# 누적 판매량이 가장 많은 제품들을 조회하는 쿼리
select 
	 dense_rank() over(order by sum(bu_amount) desc) '판매순위', product.*, sum(bu_amount) '판매량'
from 
	product 
	left join (select * from buy where bu_state in('구매', '구매확정')) bu on bu_pr_code = pr_code
group by pr_code;

# 옷 제품들 중에서 가격이 높은 순으로 제품을 조회하는 쿼리
SELECT 
    product.*
FROM
    product
	JOIN category ON ca_num = pr_ca_num
WHERE ca_name = '옷'
order by pr_price desc;

# abc123회원이 구매 목록을 조회하는 쿼리
select distinct bu_pr_code from buy where bu_me_id = 'abc123' and bu_state in('구매', '구매확정');

# abc123회원의 누적 구매 금액을 조회하는 쿼리
SELECT 
    bu_me_id '회원', format(sum(bu_amount * pr_price), 0) '누적 구매 금액'
FROM
    buy
    join product on pr_code = bu_pr_code
where bu_me_id = 'abc123' and bu_state in('구매', '구매확정');

# 회원별 누적 금액을 조회하는 쿼리
SELECT 
    me_id '회원', ifnull(format(sum(bu_amount * pr_price), 0), 0) '누적 구매 금액'
FROM
    member
    left join (select * from buy where bu_state in('구매', '구매확정')) bu on bu_me_id = me_id
    left join product on pr_code = bu_pr_code
group by me_id;