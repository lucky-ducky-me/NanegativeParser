package Parsing;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * Парсер "На негативе.ру".
 */
public class Parser {

    /**
     * Url сайта.
     */
    final String targetUrl = "https://nanegative.ru/internet-magaziny";

    /**
     * Получение отзывов.
     * @param pagesAmount количество читаемых страниц.
     * @return отзывы.
     */
    public ArrayList<StoreReview> getStoresReviews(Integer pagesAmount) {
        if (pagesAmount <= 0) {
            throw new IllegalArgumentException("Количество страниц должно быть больше нуля.");
        }

        WebDriverManager.chromedriver().setup();

        var driver = new ChromeDriver();

        var storeReviews = new ArrayList<StoreReview>();

        for (var i = 1; i <= pagesAmount; i++) {
            driver.get(targetUrl + "?page=" + i);

            if (targetUrl.equals(driver.getCurrentUrl())) {
                break;
            }

            var reviewElements = driver.findElements(By.className("find-list-box"));

            for (var reviewElement: reviewElements) {
                var targetName = getTargetName(
                        reviewElement.findElement(By.className("ss")));

                var averageRating = getAverageRating(
                        reviewElement.findElement(By.className("sro")));

                var reviewsAmount = getReviewsAmount(
                        reviewElement
                                .findElement(By.className("num")));

                storeReviews.add(
                        new StoreReview(targetName
                                , averageRating
                                , reviewsAmount));
            }
        }

        return storeReviews;
    }

    /**
     * Получение имени магазина из веб-элемента..
     * @param targetNameElement веб-элемент.
     * @return имя магазина.
     */
    private String getTargetName(WebElement targetNameElement) {
        var targetNameData = targetNameElement
                .getText().split(" ");

        var targetName = "";

        for (var i = 2; i < targetNameData.length; i++) {
            targetName += targetNameData[i];
        }

        return targetName;
    }

    /**
     * Получение среднего рейтинга из веб-элемента.
     * @param averageRatingElement веб-элемент.
     * @return средний рейтинг.
     */
    private Double getAverageRating(WebElement averageRatingElement) {
        var averageRatingData =
                averageRatingElement.getText().split(" ");

        return Double.parseDouble(
                averageRatingData[averageRatingData.length - 1]);
    }

    /**
     * Получение количества отзывов из веб-элемента.
     * @param reviewsAmountElement веб-элемент.
     * @return количество отзывов.
     */
    private Integer getReviewsAmount(WebElement reviewsAmountElement) {
        var averageRatingData =
                reviewsAmountElement.getText();

        return Integer.parseInt(averageRatingData);
    }
}
