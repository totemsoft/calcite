# Totem Software Pty Ltd

## Apache Calcite

* Single query that can talk to multiple SQL data sources such as mysql and postgres and REST.
* Wherever possible it should pushdown filters and joins to the underlying data sources instead of letting Calcite do it in memory.
* Existing Calcite Adapter needs to be tweaked and new optimization rules need to be developed.
