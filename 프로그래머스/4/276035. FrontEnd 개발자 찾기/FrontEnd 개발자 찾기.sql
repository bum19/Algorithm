-- 코드를 작성해주세요
# DISTINCT 를 꼭 쓸것
SELECT DISTINCT ID, EMAIL, FIRST_NAME, LAST_NAME
FROM DEVELOPERS D 
    JOIN
    (SELECT * FROM SKILLCODES WHERE CATEGORY = 'Front End') S
    ON (D.SKILL_CODE & S.CODE) = S.CODE
ORDER BY D.ID;

