-- 코드를 입력하세요
SELECT ANIMAL_ID, NAME
from ANIMAL_OUTS
WHERE ANIMAL_ID NOT IN (SELECT ANIMAL_ID FROM ANIMAL_INS);
