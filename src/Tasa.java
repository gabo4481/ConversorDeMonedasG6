public record Tasa(String base_code
                   ,String target_code
                   ,Double conversion_rate){
    @Override
    public String base_code() {
        return base_code;
    }

    @Override
    public String target_code() {
        return target_code;
    }

    @Override
    public Double conversion_rate() {
        return conversion_rate;
    }

    public double conversion(double cantidad, double conversion){
        return cantidad * conversion;
    }
}
