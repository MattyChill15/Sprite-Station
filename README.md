# Sprite Station — Android Ops Floor

> **Dark Ops Command Center // Hot-10 Pixel Crew // Stark Cyan Holotable**
> Digital home for a 10-agent tactical crew living on a command-center CIC floor around an etched cyan glass holotable.

---

## What’s Different from a Generic AI Dashboard

1. **Top-Down Spatial Metaphor, Not Flat Cards**:
   Instead of a generic dashboard made of cookie-cutter cards and tables, Sprite Station is an interactive physical ops floor. Anthropomorphic HD-2D-pixel crew members inhabit fixed duty pads around a Stark-style cyan glass holotable, with Chief patrolling the floor corridor.

2. **Honest Simulation & MOCK Boundaries**:
   Zero invented live metrics, zero AI hallucinations, and zero fake live counters without clear indicators. Every pane (SHORTS, ETSY, SPORTS, BURN) and CTQ line is labeled as `MOCK` per dark ops discipline (including honest zeros on carts).

3. **Strict Original Character Look-Locks**:
   Every character has locked design parameters:
   - **Chief**: Bald eagle, blue officer suit, white star insignia, stern eyes.
   - **Rogue (she/her)**: Brindle Staffy, white chest blaze, purple goggles on forehead, purple collar, charcoal jacket with safety-yellow lining, titanium wrench, mean face (never grey, never soft pant).
   - **Affleck**: Boston Terrier, chunky silver chain, immovable hard stare.
   - **Dodge**: Headset dog FACE LOCK (white blaze, amber eyes, tan eyebrow/cheek spots, black folded ears, boom mic, grey tac + hazard-red accents).
   - **Snoop**: Chill hound, dreads, glowing lightbulb beanie, boom mic.
   - **Wiz**: Raccoon bandit mask, grey beanie, aviators on rim, chain; strictly **~3/4 Cudi height**.
   - **Cudi**: Moon-dreamer wolf, navy beanie, quilted bomber, silver crescent moon pendant, half-lidded cool.
   - **Shorty**: Fox, backward red ballcap, red puffer vest, over-ear studio headphones on neck, confident smirk.
   - **Gaps**: Great-horned owl, reading spectacles, metal clipboard, severe auditor glare (never cute).
   - **Shayde (she/her)**: Black cat, piercing purple feline eyes, night-ops matte stealth suit, stern.

4. **Procedural Personality Traits**:
   Each creature features procedural, deterministic traits (Temperament, Behavioral Quirks, Signature Grunts, Current Subroutines, Tactical Focus, Caffeine / Fuel, and Vigilance Ratings).

5. **Cyan Holotable Multiplexer (Etched into Glass)**:
   The ops feed lives *in* the glass surface with perspective tilt (`rotateX`), corner reticles, scanline sweeps, and crosshairs. Tapping cycles between `SHORTS`, `ETSY`, `SPORTS`, and `BURN`, causing a 700ms soft glow pulse on the corresponding duty pad (e.g. Shorty for Shorts, Dodge for Etsy).

---

## Fold Smoke Steps (Samsung Galaxy Fold 7)

- **Folded (Tall / Narrow, ~360-400dp)**:
  1. Header, MOCK status banner, and 5x2 duty pads compress into responsive padding without clipping or horizontal overflow.
  2. Holotable text remains crisp, high-contrast, and readable.
  3. Chief's aisle patrol path auto-scales to the corridor bounds without stepping onto the cyan table glass.
- **Unfolded (Wide, ~600-800dp)**:
  1. Floor canvas expands horizontally with `widthIn(max = 680.dp)` centering for optimal viewing geometry.
  2. Spacing between duty pads expands while keeping feet grounded on pads.
  3. Holotable covers table glass width with etched 2x2 terminal panes.
- **Wiz Height Verification**:
  1. Inspect the top row of pads: Wiz on pad 5 is visibly ~3/4 the height of standard agents on the same baseline.
- **Telegram Handoff**:
  1. Tap Chief -> opens Chief's dossier -> tap `TRANSMIT VIA TELEGRAM` -> routes directly to `https://t.me/Mindbangerbot`.
  2. Tap any other agent -> shows `TELEGRAM VIA CHIEF (@Mindbangerbot)`.

---

## Roster Reference

| Callsign | Species | Duty Lane | Height Ratio | Look Lock Details |
|---|---|---|---|---|
| **CHIEF** | Bald Eagle | Strategic Ops & Uplink | 1.05x | Blue suit, white chest star, stern gaze |
| **ROGUE** | Brindle Staffy | Hardware Overhaul | 0.98x | Purple goggles, collar, wrench, mean face |
| **AFFLECK** | Boston Terrier | Perimeter Security | 0.92x | Tuxedo coat, heavy chain, hard stare |
| **DODGE** | Headset Dog | Etsy E2E Logistics | 0.98x | Face lock: blaze, tan spots, boom mic |
| **SNOOP** | Chill Hound | Audio & Signals Recon | 1.02x | Dreads, lightbulb beanie, boom mic |
| **WIZ** | Raccoon | Zero-Day Scavenger | **0.75x** | Bandit mask, aviators on beanie, chain |
| **CUDI** | Moon-Dreamer Wolf | Orbital Telemetry | 1.00x | Quilted bomber, crescent pendant, cool |
| **SHORTY** | Red Fox | Shorts Pipeline Drip | 0.90x | Backward red cap, red puffer, headphones |
| **GAPS** | Great-Horned Owl | Quality & Compliance | 1.02x | Horn tufts, glasses, metal clipboard |
| **SHAYDE** | Black Cat | Stealth Counter-Intel | 0.94x | Purple eyes, night-ops matte suit |
