Slots Game
==========

An ancient attempt to make the business layer for a slots game.

`SlotsGame` is the main interface, having two implementations `FairSlotsGame` and `UnfairSlotsGame`.
Both take a set of rules according to which they generate spins and calculate the number of accumulated points.
`FairSlotsGame` just returns the user any spin as is.
`UnfairSlotsGame` silently rejects lucky spins with a probability that is increasing the more points a user has.

