# 분류별 등록된 제품수를 조회하는 쿼리
select ca_name '분류', count(pr_code) '제품수' from category left join product on pr_ca_num = ca_num group by ca_name;