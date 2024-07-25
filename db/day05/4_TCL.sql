use product;

update product set pr_price = 1000000 where pr_code = 'AB1';

# 트랜잭션을 시작 : commit 할 때까지 최종적용이 안됨
start transaction;

update product set pr_price = 1200000 where pr_code = 'AB1';

update product set pr_price = 2200000 where pr_code = 'AB1';

update product set pr_price = 3200000 where pr_code = 'AB1';
# rollback to a2;
# rollback;

commit;

select * from product;