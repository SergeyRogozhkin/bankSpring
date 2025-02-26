package base.example.demo.dto;

import jakarta.validation.constraints.NotNull;

public class OfferDto {
    @NotNull(message = "Обязательное поле sum")
    private Long sum;

    @NotNull(message = "Обязательное поле percent")
    private Long percent;

    public OfferDto() {
    }

    public OfferDto(Long sum, Long percentile) {
        this.sum = sum;
        this.percent = percentile;
    }

    public @NotNull(message = "Обязательное поле sum") Long getSum() {
        return sum;
    }

    public void setSum(@NotNull(message = "Обязательное поле sum") Long sum) {
        this.sum = sum;
    }

    public @NotNull(message = "Обязательное поле percentile") Long getPercent() {
        return percent;
    }

    public void setPercent(@NotNull(message = "Обязательное поле percentile") Long percent) {
        this.percent = percent;
    }

}
