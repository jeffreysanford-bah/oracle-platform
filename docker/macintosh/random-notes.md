https://www.simonpcouch.com/blog/2024-03-14-oracle/

https://github.com/department-of-veterans-affairs/bip-docgen-svc/blob/development/local_setup.md

https://github.com/department-of-veterans-affairs/bip-developer-guides/wiki/Oracle-Database-On-M-Series-Macs

BUT recent slack....

Mark Mixson
I highly recommend shelving colima and using podman 5+ (using podman machine ) if you can get away with it. . . especially if you're a Mac user podman machine in podman 5+ is rewritten with the Apple Virtualization framework and seems much better than qemu/hyperkit. . . I haven't done full comprehensive tests to compare them, but it seems better.

Garrett DeZeeuw
I had a terrible time with colima trying to virtualize x86. eventually gave up and found a solution with lima directly. but then after discovering the arm64 images, i tried colima again. container creation time went from 90+ to ~8 minutes.
so whatever you do, I’d recommend running the 19.19 oracle image to avoid that CPU emulation layer

Garrett DeZeeuw
podman sounds intriguing though…

Mark Mixson
yes do not do cpu emulation unless it's absolutely the only possibility :joy:  which for a while there that was true. . . there wasn't an Oracle 19 for ARM64

Mark Mixson
yeah I was pleasantly surprised when trying out podman machine on podman 5+ -- I've used: VMWare Fusion, podman when it was qemu-based, Docker for Desktop with Hyperkit, colima/utm/etc, all kinds of things. . . and the podman 5+ VM seemed the best of them all.

Disclaimer: I have an Intel Mac still. . . but I got someone with an M2 chip to run the ARM64 Oracle DB on podman 5 and it seemed good.
