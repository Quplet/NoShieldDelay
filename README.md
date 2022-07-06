## No Shield Delay
### Configurable reduction of the delay in Minecraft's blocking system for Fabric based off of Revvilo's Responsive Shields

When holding use on an item, there is a base 'use time' which is stored and then decremented every tick while it's being held.

Minecraft will only block an attack after the shield has been raised for more than 5 ticks.
It checks this by looking at the difference between the current use time countdown and the base use time of the item.

(from LivingEntity's isBlocking() method)

> `return item.getMaxUseTime(this.activeItemStack) - this.itemUseTimeLeft >= 5;`

This mod bypasses this by using mixin to alter the `5` constant, allowing comparison between any given integer 0 thorough 5.

This mod is supposed to be an alternative to Revvilo's Responsive Shields. Because of this, if Revvilo ever makes a Fabric port of their own, I will cease maintaining this project.

----------
## Setup

This mod should work with 1.17+. It possibly could work with versions older than that, but I don't feel like testing it.

This mod is only necessary server side. Installing on the client as well will not yield any benefit.

The Fabric API is not needed for this mod. Only the Fabric Loader.

----------
## Configs
### Files and contents:
#### no-shield-delay.toml

>`RaiseTime`
>> (From 0 to 5. default: 0) The amount of time, in ticks, from when you right-click before the game will block attacks.

>`Enabled`
>> (Default: true) Whether or not the effect of the mod is enabled.


### Info:
- Setting Raise Time to 0 makes shields capable of blocking immediately

- The client-side raise animation takes roughly one tick to complete. So setting Raise Time to 1 is a decent balance between responsiveness and not being able to blockhit as easily.