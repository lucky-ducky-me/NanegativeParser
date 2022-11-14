package Parsing;

import lombok.Getter;

/**
 * Отзыв о магазине.
 */
@Getter
public class StoreReview {

    /**
     * Имя магазина.
     */
    String targetName;

    /**
     * Средний рейтинг.
     */
    Double averageRating;

    /**
     * Количество отзывов.
     */
    Integer reviewsAmount;

    private void setTargetName(String targetName) {
        if (targetName == null || targetName.isEmpty()) {
            throw new IllegalArgumentException("Имя магазина не определено.");
        }

        this.targetName = targetName;
    }

    private void setAverageRating(Double averageRating) {
        if (averageRating < 0.0) {
            throw new IllegalArgumentException("Рейтинг не может быть меньше нуля.");
        }

        this.averageRating = averageRating;
    }

    private void setReviewsAmount(Integer reviewsAmount) {
        if (reviewsAmount < 0) {
            throw new IllegalArgumentException("Количество отзывов " +
                    "не может быть меньше нуля.");
        }

        this.reviewsAmount = reviewsAmount;
    }

    /**
     * Создание отзыва об магазине.
     * @param targetName имя магазина.
     * @param averageRating средняя оценка.
     * @param reviewsAmount количество отзывов.
     */
    public StoreReview(String targetName, Double averageRating, Integer reviewsAmount) {
        setTargetName(targetName);
        setAverageRating(averageRating);
        setReviewsAmount(reviewsAmount);
    }
}
