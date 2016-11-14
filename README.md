# uc-union-ad-hook
this is a project for union-ad develope test  

union request adn follow this order: Admob -> Facebook -> union  
if Admob fill the ad, the facebook and union will not be invoke, if it not fill, then facebook,then union
sometime , the CP always get union-ad ,them want to know , why, why admob fail to fill.

when i debug this issue ,found that the old union-sdk version, when admob and adsence fail, sdk didnot print the log, so I can not to find the reason why admob not fill.  
this is the reason why this project here, I need to hook admob and adsence callback, to print the error code

and through the development, i found it also can be a switch to turn admob/adsence off, when I hook the initial the adn, then give it a wrong placementID, this case can be used on this situation: when I want to debug the facebook adsence,but the admob always fill the ad, so i need to turn off the admob.

there maybe some other usetage is waiting everybody to find out.

### What already Done  
- Xposed hook
- Hook Admob Banner
- Hook Admob Interstitial
- Hook Union Interstitial

### Todo  
- Admob Native
- Facebook Banner
- Facebook Interstitial
- Facebook Native
- Union Banner
- Union Native
- Integration LeanCloud【 I dont want to restart device over and over again,when set the class name】
