firewall {
    all-ping enable
    broadcast-ping disable
    ipv6-receive-redirects disable
    ipv6-src-route disable
    ip-src-route disable
    log-martians enable
    name WAN_IN {
        default-action drop
        enable-default-log
        rule 1 {
            action accept
            description "Allow established connections"
            state {
                established enable
                related enable
            }
        }
        rule 2 {
            action drop
            description "Drop invalid state"
            log enable
            state {
                invalid enable
            }
        }
    }
    name WAN_LOCAL {
        default-action drop
        enable-default-log
        rule 1 {
            action accept
            description "Allow established connections"
            state {
                established enable
                related enable
            }
        }
        rule 2 {
            action drop
            description "Drop invalid state"
            log enable
            state {
                invalid enable
            }
        }
    }
    receive-redirects disable
    send-redirects enable
    source-validation disable
    syn-cookies enable
}
interfaces {
    ethernet eth0 {
        address dhcp
        description WAN
        duplex auto
        firewall {
            in {
                name WAN_IN
            }
            local {
                name WAN_LOCAL
            }
        }
        poe {
            output off
        }
        speed auto
    }
    ethernet eth1 {
        duplex auto
        poe {
            output off
        }
        speed auto
    }
    ethernet eth2 {
        duplex auto
        poe {
            output off
        }
        speed auto
    }
    ethernet eth3 {
        duplex auto
        poe {
            output off
        }
        speed auto
    }
    ethernet eth4 {
        duplex auto
        poe {
            output off
        }
        speed auto
    }
    loopback lo {
    }
    switch switch0 {
        address 192.168.1.1/24
        description LAN
        ip {
        }
        mtu 1500
        switch-port {
            interface eth2 {
            }
            interface eth3 {
            }
            interface eth4 {
            }
            vlan-aware disable
        }
    }
}
service {
    dhcp-server {
        disabled false
        hostfile-update disable
        shared-network-name LAN {
            authoritative enable
            subnet 192.168.1.0/24 {
                default-router 192.168.1.1
                dns-server 192.168.1.1
                lease 86400
                start 192.168.1.150 {
                    stop 192.168.1.254
                }
            }
        }
        use-dnsmasq disable
    }
    dns {
        forwarding {
            cache-size 150
            listen-on switch0
        }
    }
    gui {
        http-port 80
        https-port 443
        older-ciphers enable
    }
    nat {
        rule 5010 {
            description "Masquerade for WAN"
            outbound-interface eth0
            type masquerade
        }
    }
    ssh {
        port 22
        protocol-version v2
    }
}
system {
    host-name EdgeOfWindsor
    login {
        user awalters96 {
            authentication {
                encrypted-password $6$39MLmgGSpcEaDfOP$R6dkAx1uSIgW7Hi9n5CtHUfojOsTtn1DJN5yPI625LVE6dj0D6IKEmb93N6Fovx1rHbx/hcmPAl1CsLihYaG/1
                plaintext-password ""
            }
            full-name "Drew Walters"
            level admin
        }
        user cwalters68 {
            authentication {
                encrypted-password $6$GrROlz.Sm11T$Yy8NCH8ViSAfJXwQmACm2JxX3v33IGrJMG.zOsAbmyJMAPxszmcaHIfxqFe6rlFa8FIJmUeVMp6rLDMfZz6vy1
                plaintext-password ""
            }
            full-name "Chris Walters"
            level admin
        }
    }
    ntp {
        server 0.ubnt.pool.ntp.org {
        }
        server 1.ubnt.pool.ntp.org {
        }
        server 2.ubnt.pool.ntp.org {
        }
        server 3.ubnt.pool.ntp.org {
        }
    }
    package {
        repository wheezy {
            components "main contrib non-free"
            distribution wheezy
            password ""
            url http://http.us.debian.org/debian
            username ""
        }
    }
    syslog {
        global {
            facility all {
                level notice
            }
            facility protocols {
                level debug
            }
        }
    }
    time-zone UTC
}


/* Warning: Do not remove the following line. */
/* === vyatta-config-version: "config-management@1:conntrack@1:cron@1:dhcp-relay@1:dhcp-server@4:firewall@5:ipsec@5:nat@3:qos@1:quagga@2:system@4:ubnt-pptp@1:ubnt-util@1:vrrp@1:webgui@1:webproxy@1:zone-policy@1" === */
/* Release version: v1.9.1.1.4977347.170426.0359 */
