-- 코드를 입력하세요
SELECT FO.PRODUCT_ID, PRODUCT_NAME, AMOUNT * PRICE TOTAL_SALES
FROM (SELECT PRODUCT_ID, SUM(AMOUNT) AMOUNT
      FROM FOOD_ORDER
      WHERE YEAR(PRODUCE_DATE) = 2022 AND MONTH(PRODUCE_DATE) = 5
      GROUP BY PRODUCT_ID) FO 
      INNER JOIN FOOD_PRODUCT FP
      ON FO.PRODUCT_ID = FP.PRODUCT_ID
ORDER BY TOTAL_SALES DESC, FO.PRODUCT_ID;
