package base.example.demo.dto;

import jakarta.validation.constraints.NotNull;

public class OfferDto {
    @NotNull(message = "Обязательное поле sum")
    private Long sum;

    @NotNull(message = "Обязательное поле percentile")
    private Long percentile;

    public OfferDto() {}

    public OfferDto(Long sum, Long percentile) {
        this.sum = sum;
        this.percentile = percentile;
    }

    public @NotNull(message = "Обязательное поле sum") Long getSum() {
        return sum;
    }

    public void setSum(@NotNull(message = "Обязательное поле sum") Long sum) {
        this.sum = sum;
    }

    public @NotNull(message = "Обязательное поле percentile") Long getPercentile() {
        return percentile;
    }

    public void setPercentile(@NotNull(message = "Обязательное поле percentile") Long percentile) {
        this.percentile = percentile;
    }

}
