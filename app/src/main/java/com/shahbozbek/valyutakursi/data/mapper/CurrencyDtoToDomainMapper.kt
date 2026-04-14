package com.shahbozbek.valyutakursi.data.mapper

import com.shahbozbek.valyutakursi.data.model.CurrencyDto
import com.shahbozbek.valyutakursi.domain.model.Currency

fun CurrencyDto.toDomain() = Currency(
    id = id,
    Code = Code,
    Ccy = Ccy,
    CcyNm_EN = CcyNm_EN,
    CcyNm_RU = CcyNm_RU,
    CcyNm_UZ = CcyNm_UZ,
    CcyNm_UZC = CcyNm_UZC,
    Date = Date,
    Diff = Diff,
    Nominal = Nominal,
    Rate = Rate
)