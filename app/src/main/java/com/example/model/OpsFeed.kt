package com.example.model

/**
 * Holotable Ops Multiplexer Feed
 * 4 Panes: SHORTS, ETSY, SPORTS, BURN
 * Status is strictly MOCK per master build instructions.
 */
enum class OpsPaneType(
    val title: String,
    val primaryAgentId: String,
    val summary: String,
    val metricA: Pair<String, String>,
    val metricB: Pair<String, String>,
    val metricC: Pair<String, String>,
    val statusTag: String
) {
    SHORTS(
        title = "SHORTS",
        primaryAgentId = "shorty",
        summary = "Content Ingestion & Viral Drop Queue",
        metricA = "BANK COUNT (MOCK)" to "14 READY",
        metricB = "NEXT DRIP (MOCK)" to "T-18m 42s",
        metricC = "LAST URL STUB" to "/s/v92x-alpha",
        statusTag = "AWAITING GAPS CLEAR"
    ),
    ETSY(
        title = "ETSY",
        primaryAgentId = "dodge",
        summary = "E2E Tactical Merch & Logistics Matrix",
        metricA = "VISITS (MOCK)" to "142 NODES",
        metricB = "CART STUB (MOCK)" to "0 (HONEST ZERO)",
        metricC = "ACTIVE LISTING" to "TAC-PATCH-V2",
        statusTag = "ROUTING NORMAL"
    ),
    SPORTS(
        title = "SPORTS",
        primaryAgentId = "affleck",
        summary = "Odds Horizon & Manual Paper Stakes",
        metricA = "OPEN TICKETS (MOCK)" to "3 ACTIVE",
        metricB = "BANKROLL STUB (MOCK)" to "2,450u ALLOC",
        metricC = "SPREAD LOCK" to "+4.5 CHIEF PICK",
        statusTag = "RISK MITIGATED"
    ),
    BURN(
        title = "BURN",
        primaryAgentId = "chief",
        summary = "Cursor Ultra Engine & Token Meter",
        metricA = "TOKEN USAGE (MOCK)" to "4.2k ULTRA",
        metricB = "BILLING CYCLE (MOCK)" to "DAY 12 OF 30",
        metricC = "ENGINE STATUS" to "ULTRA-9 NOMINAL",
        statusTag = "UNDER CEILING"
    );

    fun next(): OpsPaneType {
        val values = entries
        return values[(ordinal + 1) % values.size]
    }
}

data class OpsFeedData(
    val selectedPane: OpsPaneType = OpsPaneType.SHORTS,
    val marqueeTicks: List<String> = listOf(
        "[MOCK CTQ] Y1 BURN: 4.2k ULTRA",
        "[MOCK CTQ] Y2 COVER-COST: $0 NET",
        "[MOCK CTQ] Y3 SHORTS BANK: 14 READY",
        "[MOCK CTQ] Y4 QE: GREEN VERIFIED",
        "[MOCK SIM] ALL SENSORS CALIBRATED",
        "[MOCK SYS] LATENCY: 12ms STABLE"
    )
)
