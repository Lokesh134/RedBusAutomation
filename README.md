RedBus Automation Challenge – Infinite Scroll & Filter Validation
📌 Problem Statement

The goal of this automation challenge is to validate and extract all available bus results from the RedBus search results page after applying specific filters.

The RedBus UI uses dynamic, lazy-loaded content where bus results are loaded incrementally as the user scrolls. Traditional Selenium approaches such as direct scrolling to the bottom or relying on end-of-list markers do not work reliably.

This challenge focuses on designing a robust, optimized, and production-ready Selenium solution that can handle modern React-based infinite scrolling behavior.

🎯 Objective

Search for buses between two cities (e.g., Hyderabad → Bangalore)

Apply filters:

Primo buses

Evening departure (18:00–24:00)

Load all available bus results dynamically

Print:

Bus operator names

Total number of buses loaded

Ensure the automation result matches the UI count

⚠️ Key Challenges Identified

Lazy-loaded infinite scrolling

Bus results are loaded in batches (~10 at a time)

Data loads only when the viewport crosses specific thresholds

React-based DOM re-rendering

Scrolling destroys and recreates DOM nodes

Cached WebElement references become stale

No reliable “end of list” indicator

No deterministic marker to detect when all buses are loaded

Footer visibility does not guarantee completion

Variable network and render latency

Bus loading speed differs across routes and times

🛠️ Automation Concepts Used
✅ Selenium & Java

Selenium WebDriver (ChromeDriver)

Explicit waits (WebDriverWait)

JavaScript execution (JavascriptExecutor)

Page Object Model (POM)

✅ Advanced Synchronization Techniques

Count-based stabilization instead of static waits

Avoidance of StaleElementReferenceException

DOM re-query after each scroll

Controlled wait cycles instead of hard sleeps

✅ Design Principles

Single Responsibility Principle (POM separation)

No WebElement caching across scrolls

Graceful exit conditions

Human-like interaction simulation

🚀 Optimized Solution Approach
🔑 Core Strategy

Scroll gradually and stop only when the number of loaded buses stops increasing for multiple cycles.

Instead of:

Jumping to page bottom

Using fixed sleeps

Waiting for non-existent “end of list” elements

We implemented:

Incremental scrolling (scrollBy)

Dynamic re-evaluation of result count

Stabilization-based termination

🔄 Final Scrolling Algorithm

Fetch current number of loaded buses

Scroll down slightly (human-like)

Allow time for lazy-loaded content to render

Re-fetch bus count

Repeat until the count stabilizes over multiple iterations

This guarantees:

All batches are loaded

No skipped content

No infinite loops

No session crashes

📈 Results Achieved

Successfully loaded 100% of available buses

Automation output matched the UI bus count (e.g., 66+ buses)

Eliminated:

Stale element exceptions

Timeout exceptions

Premature termination

Achieved a stable, reusable, and scalable solution

⭐ Key Learnings

Infinite scroll cannot be automated using traditional scrolling techniques

Modern UIs require viewport-aware, event-driven automation

Selenium stability improves drastically when:

WebElements are not cached

Scrolling mimics real user behavior

Count-based logic is more reliable than DOM markers
