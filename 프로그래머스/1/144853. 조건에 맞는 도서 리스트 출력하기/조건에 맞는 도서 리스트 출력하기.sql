-- 코드를 입력하세요
SELECT BOOK_ID AS ID, date_format(PUBLISHED_DATE, '%Y-%m-%d') as PUBLISHED_DATE
from book
where category = "인문" and year(published_date) = 2021
order by published_date;

# where category = "인문" and published_date < "2022-01-01" and published_date >= "2022-01-01";
