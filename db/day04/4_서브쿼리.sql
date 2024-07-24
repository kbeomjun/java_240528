# any(some), in, all은 서브쿼리와 함께 사용
# 속성 비교 any(값1, 값2, ...), 속성 비교 some(값1, 값2, ...)
# 속성 비교 any(서브쿼리)
# 속성값이 값들 중 하나 이상의 값들을 만족할 때 사용

# in(값1, 값2, ...)
# 속성 in(서브쿼리)
# in은 = any와 같음
# 속성값이 값들 중에 있으면 참

# 속성 비교 all(값1, 값2, ...)
# 속성 비교 all(서브쿼리)
# 속성값이 모든 값들을 만족할때 사용

#  가전 제품들의 가격들보다 비싼 제품들을 조회
SELECT 
    *
FROM
    product
where pr_price > any(select pr_price from product where pr_ca_code = 'AB');

# 의류의 가장 싼 제품보다 비싼 제품을 조회하는 쿼리 (단, 의류는 CD, 의류 제품은 제외)
SELECT 
    *
FROM
    product
where pr_price > any(select pr_price from product where pr_ca_code = 'CD') and pr_ca_code not in('CD');

# 가전제품과 가격이 일치하는 제품들을 조회하는 쿼리(단, 가전제품은 제외)
SELECT 
    *
FROM
    (select * from product where pr_ca_code != 'AB') pr
where pr_price = any(select pr_price from product where pr_ca_code = 'AB');