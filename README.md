## No Shield Delay
### Configurable reduction of the delay in Minecraft's blocking system for Fabric based off of Revvilo's Responsive Shields

When holding use on an item, there is a base 'use time' which is stored and then decremented every tick while it's being held.

Minecraft will only block an attack after the shield has been raised for more than 5 ticks.
It checks this by looking at the difference between the current use time countdown and the base use time of the item.

(from LivingEntity's isBlocking() method)

> `return item.getMaxUseTime(this.activeItemStack) - this.itemUseTimeLeft >= 5;`

This mod bypasses this by using mixin to alter the result of this block of code, allowing comparison between any given integer 0 thorough 5.



----------
## Configs
### Files and contents:
#### no-shield-delay.toml

>`RaiseTime`
>> (From 0 to 5. default: 0) The amount of time, in ticks, from when you right-click before the game will block attacks.

>`Enabled`
>> (Default: true) Whether or not the effect of the mod is enabled.


### Info:
- Setting Raise Time to 0 makes shields capable of blocking immediately.


- Setting Raise Time to 5 is no different from not having the mod installed and is less efficient than setting the Enable setting to false. Both have the effect of disabling the mod's effects.


- The client-side raise animation takes roughly one tick to complete. So setting Raise Time to 1 is a decent balance between responsiveness and not being able to blockhit as easily - which I think was the original intention of the delay.