-- 코드를 입력하세요
select member_name, review_text, date_format(review_date, "%Y-%m-%d") review_date
from rest_review r join member_profile p on r.member_id =  p.member_id
where r.member_id = 
    (select member_id 
    from rest_review
    group by member_id
    order by count(*) desc
    limit 1)
order by review_date, review_text;
