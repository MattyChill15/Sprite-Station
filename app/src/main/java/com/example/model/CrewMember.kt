package com.example.model

/**
 * Hot-10 Crew Member model with strict look-locks and procedural personality traits.
 *
 * Look locks (original characters, no celebrity likenesses, no Nintendo/Star Fox/Pokémon IP):
 * - Chief: Cap-energy bald eagle, blue suit, white star on chest, stern.
 * - Rogue (she/her): Brindle Staffy, white chest blaze, purple goggles on forehead,
 *   purple collar, charcoal jacket yellow collar lining, wrench, mean face (not soft pant; not grey).
 * - Affleck: Boston Terrier, chain, hard stare.
 * - Dodge: Headset dog FACE LOCK: white blaze, amber eyes, tan eyebrow/cheek spots,
 *   black ears, boom mic, grey tac + red accents, intense stare (not smile; not Heeler/Jack Russell remix).
 * - Snoop: Chill dog, dreads, lightbulb beanie, boom mic (chill OK).
 * - Wiz: Raccoon, beanie, aviators on beanie, chain; ~3/4 Cudi height.
 * - Cudi: Moon-dreamer wolf, navy beanie, quilted bomber, crescent pendant, half-lidded cool.
 * - Shorty: Fox, red cap BACKWARD, red puffer, headphones on neck, cool smirk (not soft-cute).
 * - Gaps: Great-horned owl, glasses, clipboard, stern (never cute owl flash).
 * - Shayde (she/her): Black cat, purple eyes, night-ops, stern.
 */
data class CrewMember(
    val id: String,
    val callsign: String,
    val codename: String,
    val species: String,
    val genderPronouns: String = "they/them",
    val role: String,
    val dutyLane: String,
    val heightScale: Float = 1.0f, // Wiz is 0.75f relative to standard
    val telegramUplink: String = "TELEGRAM VIA CHIEF",
    val isChief: Boolean = false,
    val relatedOpsPane: String? = null, // Shorts, Etsy, Sports, Burn
    val lookLockSummary: String,
    // Procedural Personality Traits
    val temperament: String,
    val quirk: String,
    val signatureGrunt: String,
    val currentSubroutine: String,
    val vigilanceRating: Int, // 1-100
    val tacticalFocus: String,
    val caffeineLevel: String,
    val opsClearance: String
)

object CrewRoster {
    val members = listOf(
        CrewMember(
            id = "chief",
            callsign = "CHIEF",
            codename = "Apex-01",
            species = "Bald Eagle",
            genderPronouns = "he/him",
            role = "Station Commander & Single Status Surface",
            dutyLane = "Strategic Ops & Master Uplink",
            heightScale = 1.05f,
            telegramUplink = "https://t.me/Mindbangerbot",
            isChief = true,
            relatedOpsPane = "BURN",
            lookLockSummary = "Cap-energy bald eagle, deep blue officer suit, white star insignia on chest, razor-sharp stern gaze.",
            temperament = "Vigilant Commander / Stoic Sentinel",
            quirk = "Never rests; monitors all 4 panes simultaneously while pacing aisle.",
            signatureGrunt = "Low eagle screech under breath when latency hits 40ms.",
            currentSubroutine = "Aisle patrol / Global burn review / Uplink handshake",
            vigilanceRating = 99,
            tacticalFocus = "Fleet Readiness & Command Autonomy",
            caffeineLevel = "Black Coffee, 0 Kelvin",
            opsClearance = "OMEGA-LEVEL"
        ),
        CrewMember(
            id = "rogue",
            callsign = "ROGUE",
            codename = "Torque-09",
            species = "Brindle Staffy",
            genderPronouns = "she/her",
            role = "Lead Fabrication & Heavy Systems",
            dutyLane = "Hardware Overhaul & Structural Rigging",
            heightScale = 0.98f,
            relatedOpsPane = "BURN",
            lookLockSummary = "Brindle coat with white chest blaze, purple tinted goggles pushed up, purple heavy collar, charcoal jacket with safety-yellow lining, titanium wrench, mean scowl.",
            temperament = "Aggressive Pragmatist / Iron-Willed",
            quirk = "Taps wrench rhythmically to 120bpm against floor struts when diagnosing.",
            signatureGrunt = "Deep guttural snarl when substandard fittings are detected.",
            currentSubroutine = "Torque check on Holotable heat sink / Subfloor calibration",
            vigilanceRating = 94,
            tacticalFocus = "Structural Integrity & Zero Mechanical Tolerance",
            caffeineLevel = "Triple Espresso in motor mug",
            opsClearance = "TIER-1 ARCHITECT"
        ),
        CrewMember(
            id = "affleck",
            callsign = "AFFLECK",
            codename = "Bulldozer-04",
            species = "Boston Terrier",
            genderPronouns = "he/him",
            role = "Perimeter Security & Tactical Enforcement",
            dutyLane = "Threat Interdiction & Risk Mitigation",
            heightScale = 0.92f,
            relatedOpsPane = "SPORTS",
            lookLockSummary = "Boston Terrier, polished heavy chain, immovable hard stare, tactical black harness.",
            temperament = "Unyielding Enforcer / Zero-Nonsense",
            quirk = "Fixed unblinking stare toward any door entry vector.",
            signatureGrunt = "Short rasping huff before perimeter sweeps.",
            currentSubroutine = "Perimeter scan / Threat telemetry correlation",
            vigilanceRating = 96,
            tacticalFocus = "Close-Quarters Deterrence & Asset Lockdown",
            caffeineLevel = "Cold Brew concentrate",
            opsClearance = "TIER-1 SECURITY"
        ),
        CrewMember(
            id = "dodge",
            callsign = "DODGE",
            codename = "Dispatch-03",
            species = "Headset Dog",
            genderPronouns = "he/him",
            role = "Etsy E2E Operations & Logistics Router",
            dutyLane = "Full-Cycle Pipeline & Supply Chains",
            heightScale = 0.98f,
            relatedOpsPane = "ETSY",
            lookLockSummary = "Distinctive face lock: white blaze, amber eyes, tan eyebrow and cheek spots, black folded ears, boom mic, grey tactical harness with hazard-red accents, intense locked-in stare.",
            temperament = "Hyper-Focused Dispatcher / Relentless",
            quirk = "Constantly adjusts boom mic angle whenever order queue changes.",
            signatureGrunt = "Rapid double-click on mic switch: 'Package rolling.'",
            currentSubroutine = "E2E order dispatch / Shipping matrix sync / Cart stub polling",
            vigilanceRating = 92,
            tacticalFocus = "Zero Friction Logistics & Fulfillment Routing",
            caffeineLevel = "Yerba Mate drip",
            opsClearance = "DISPATCH ALPHA"
        ),
        CrewMember(
            id = "snoop",
            callsign = "SNOOP",
            codename = "Sonar-07",
            species = "Chill Hound",
            genderPronouns = "he/him",
            role = "Audio & Signals Reconnaissance",
            dutyLane = "Spectrum Analysis & Low-Fi Wiretap",
            heightScale = 1.02f,
            relatedOpsPane = "SPORTS",
            lookLockSummary = "Chill hound, dark dreads, glowing lightbulb motif beanie, boom mic, effortless relaxed slouch.",
            temperament = "Harmonic Chill / Unshakable Calm",
            quirk = "Subtly bobs head to inaudible sub-bass transmissions.",
            signatureGrunt = "Smooth baritone: 'Signals copy that.'",
            currentSubroutine = "Filtering radio static / Ambient noise gate tuning",
            vigilanceRating = 87,
            tacticalFocus = "Acoustic Surveillance & Frequency Intercept",
            caffeineLevel = "Green tea infused with mint",
            opsClearance = "SIGINT CLEAR"
        ),
        CrewMember(
            id = "wiz",
            callsign = "WIZ",
            codename = "ZeroDay-06",
            species = "Raccoon",
            genderPronouns = "he/him",
            role = "Code Breaker & Zero-Day Scavenger",
            dutyLane = "Cryptographic Probes & Memory Infiltration",
            heightScale = 0.75f, // STRICT HEIGHT LOCK: ~3/4 Cudi height
            relatedOpsPane = "BURN",
            lookLockSummary = "Raccoon bandit mask, grey watch beanie, retro aviators perched on beanie rim, chunky silver chain, visibly shorter stature.",
            temperament = "Cunning Opportunist / High-Velocity Analyst",
            quirk = "Rummages through discarded logic gates; flicks aviator frames when compiling.",
            signatureGrunt = "Snicker followed by lightning keystrokes.",
            currentSubroutine = "Memory dump forensics / Firmware decompression",
            vigilanceRating = 91,
            tacticalFocus = "Exploit Neutralization & Cryptographic Heuristics",
            caffeineLevel = "Crushed guarana beans",
            opsClearance = "CYBER LEVEL 4"
        ),
        CrewMember(
            id = "cudi",
            callsign = "CUDI",
            codename = "Orbital-05",
            species = "Moon-Dreamer Wolf",
            genderPronouns = "he/him",
            role = "Night-Shift Operations & Orbital Telemetry",
            dutyLane = "Deep Space Relay & Stellar Coordination",
            heightScale = 1.0f,
            relatedOpsPane = "SPORTS",
            lookLockSummary = "Wolf with silver-tipped pelt, deep navy beanie, quilted dark bomber jacket, silver crescent moon pendant, half-lidded cool expression.",
            temperament = "Contemplative Visionary / Nocturnal Anchor",
            quirk = "Eyes drift toward upper perimeter sensors when telemetry locks.",
            signatureGrunt = "Low resonant hum in key of D minor.",
            currentSubroutine = "Orbital downlink validation / Odds line evaluation",
            vigilanceRating = 89,
            tacticalFocus = "Stellar Trajectories & Long-Wave Forecasts",
            caffeineLevel = "Chamomile / Earl Grey blend",
            opsClearance = "ORBITAL PRIME"
        ),
        CrewMember(
            id = "shorty",
            callsign = "SHORTY",
            codename = "Flash-02",
            species = "Fox",
            genderPronouns = "he/him",
            role = "Shorts Pipeline & High-Velocity Content Drip",
            dutyLane = "Render Ingestion & Drip Staging",
            heightScale = 0.90f,
            relatedOpsPane = "SHORTS",
            lookLockSummary = "Sleek red fox, red ballcap worn backward, vibrant red puffer vest, over-ear studio headphones resting around neck, confident smirk.",
            temperament = "Quick-Witted Maverick / High-Energy",
            quirk = "Constantly counts down render seconds on fingertips.",
            signatureGrunt = "Playful bark: 'Ready to drop when Gaps clears.'",
            currentSubroutine = "Batch 14 render queue / Next drip countdown: 18m",
            vigilanceRating = 90,
            tacticalFocus = "Instantaneous Viral Ingestion & Frame Cadence",
            caffeineLevel = "Energy drink + orange citrus",
            opsClearance = "MEDIA ALPHA"
        ),
        CrewMember(
            id = "gaps",
            callsign = "GAPS",
            codename = "Audit-08",
            species = "Great-Horned Owl",
            genderPronouns = "he/him",
            role = "Quality Assurance & Compliance Clear",
            dutyLane = "Final Verification & Protocol Enforcement",
            heightScale = 1.02f,
            relatedOpsPane = "BURN",
            lookLockSummary = "Great-horned owl, horn feather tufts, round reading spectacles, heavy metal clipboard, strictly severe expression, never cute.",
            temperament = "Methodical Auditor / Incorruptible Judge",
            quirk = "Rotates head a crisp 90 degrees before stamping any clearance.",
            signatureGrunt = "Dry authoritative snap of clipboard latch.",
            currentSubroutine = "Shorts drip protocol verification / Burn rate check",
            vigilanceRating = 98,
            tacticalFocus = "Zero-Flaw Validation & Pre-Release Vetting",
            caffeineLevel = "Oolong tea steeped at precisely 85°C",
            opsClearance = "FINAL AUDIT AUTHORITY"
        ),
        CrewMember(
            id = "shayde",
            callsign = "SHAYDE",
            codename = "Phantom-10",
            species = "Black Cat",
            genderPronouns = "she/her",
            role = "Stealth Infiltration & Counter-Intel",
            dutyLane = "Electronic Camouflage & Threat Recon",
            heightScale = 0.94f,
            relatedOpsPane = "SHORTS",
            lookLockSummary = "Sleek midnight-black fur, piercing purple feline eyes, night-ops matte tactical suit, quiet feline posture, deadpan stern focus.",
            temperament = "Silent Shadow / Razor-Sharp Professional",
            quirk = "Steps soundlessly across steel plates; tail twitches once per security ping.",
            signatureGrunt = "Soft purr like a silenced suppressed engine.",
            currentSubroutine = "Passive optical sweep / Counter-surveillance perimeter",
            vigilanceRating = 97,
            tacticalFocus = "Low-Signature Operations & Perimeter Infiltration",
            caffeineLevel = "Pure spring water, chilled",
            opsClearance = "BLACK OPS LEVEL 9"
        )
    )

    fun findById(id: String): CrewMember {
        return members.find { it.id == id } ?: members.first()
    }
}
